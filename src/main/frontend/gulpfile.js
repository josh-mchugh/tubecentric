var gulp = require('gulp');
var del = require('del');
var concat = require('gulp-concat');
var exec = require('gulp-exec');
var size = require('gulp-size');

/*
* Dist
*/
gulp.task('clean:dist', function () {

    return del(['dist/**/*']);
});

/*
* Resources
*/
gulp.task('clean:resources:node', function() {

    return del(['styles/node_modules/**/*']);
});

gulp.task('clean:resources:dist', function() {

    return del(['styles/dist/**/*']);
});

gulp.task('build:resources:install:node', function() {

    return gulp.src('./styles')
        .pipe(exec('cd styles && npm install'));
});

gulp.task('build:resources', function() {

    return gulp.src('./styles')
        .pipe(exec('cd styles && npm run build'));
});

gulp.task('copy:resources:css', function() {

    return gulp.src('styles/dist/**.css')
        .pipe(gulp.dest('./dist/styles'))
        .pipe(size());
});

gulp.task('copy:resources:js', function() {

    return gulp.src('styles/dist/**.js')
        .pipe(gulp.dest('./vendor/'))
        .pipe(size());
})

gulp.task('build:resources:prod', gulp.series(
    'clean:resources:node',
    'clean:resources:dist',
    'build:resources:install:node',
    'build:resources',
    'copy:resources:css',
    'copy:resources:js'
));

gulp.task('build:resources:dev', gulp.series(
    'clean:resources:dist',
    'build:resources',
    'copy:resources:css',
    'copy:resources:js'
));

/*
 * App: Video Meta Builder
 */

gulp.task('clean:apps:videoMetaBuilderApp:dist', function () {

    return del(['apps/videoMetaBuilderApp/dist/**/*']);
});

gulp.task('clean:app:videoMetaBuilderApp:external-css', function() {

    return del(['apps/videoMetaBuilderApp/src/assets/**/*']);
});

gulp.task('clean:apps:videoMetaBuilderApp:node', function() {

    return del(['apps/videoMetaBuilderApp/node_modules']);
});

gulp.task('build:apps:videoMetaBuilderApp:install:node', function() {

    return gulp.src('apps/videoMetaBuilderApp')
        .pipe(exec('cd apps/videoMetaBuilderApp && npm install'));
});

gulp.task('build:apps:videoMetaBuilderApp:external-css', function() {

    return gulp.src('./styles/dist/landing.css')
        .pipe(gulp.dest('./apps/videoMetaBuilderApp/src/assets'));
});

gulp.task('build:apps:videoMetaBuilderApp:build:dev', function() {

    return gulp.src('apps/videoMetaBuilderApp')
        .pipe(exec('cd apps/videoMetaBuilderApp && npm run build'));
});

gulp.task('build:apps:videoMetaBuilderApp:build:prod', function() {

    return gulp.src('apps/videoMetaBuilderApp')
        .pipe(exec('cd apps/videoMetaBuilderApp && npm run build:prod '));
});

gulp.task('copy:apps:videoMetaBuilderApp:js', function() {

    return gulp.src(['./apps/videoMetaBuilderApp/dist/videoMetaBuilderApp/scripts.js', './apps/videoMetaBuilderApp/dist/videoMetaBuilderApp/**-es2015.js'])
             //.pipe(concat('main.js'))
            .pipe(gulp.dest('./dist/videoMetaBuilderApp'))
            .pipe(size());
});


gulp.task('build:apps:videoMetaBuilderApp:dev', gulp.series(
    'clean:app:videoMetaBuilderApp:external-css',
    'build:apps:videoMetaBuilderApp:external-css',
    'build:apps:videoMetaBuilderApp:build:dev',
));

gulp.task('build:aps:videoMetaBuilderApp:prod', gulp.series(
    'clean:apps:videoMetaBuilderApp:dist',
    'clean:app:videoMetaBuilderApp:external-css',
    'clean:apps:videoMetaBuilderApp:node',
    'build:apps:videoMetaBuilderApp:install:node',
    'build:apps:videoMetaBuilderApp:build:prod',
    'copy:apps:videoMetaBuilderApp:js'
));

/*
* Build All
*/
gulp.task('build:all:prod', gulp.series(
    'clean:dist',
    'build:resources:prod'
));
