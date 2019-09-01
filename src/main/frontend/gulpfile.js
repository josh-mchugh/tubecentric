var gulp = require('gulp');
var del = require('del');
var concat = require('gulp-concat');
var exec = require('gulp-exec');
var size = require('gulp-size');
var watch = require('gulp-watch');

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

gulp.task('clean:resources:all', gulp.series(
    'clean:resources:dist',
    'clean:resources:node'
));

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

gulp.task('copy:resources:images', function () {

    return gulp.src('styles/src/resources/images/**.*')
        .pipe(gulp.dest('./dist/images'))
        .pipe(size());
});

gulp.task('copy:resources:fonts', function() {

    return gulp.src('styles/node_modules/@fortawesome/fontawesome-free/webfonts/**.*')
        .pipe(gulp.dest('./dist/fonts'))
        .pipe(size());
});

gulp.task('copy:resources:js', function() {

    return gulp.src('styles/dist/**.js')
        .pipe(gulp.dest('./dist/vendor/'))
        .pipe(size());
});

gulp.task('copy:resources:dev', function() {

    return gulp.src(['./dist/**/*',])
        .pipe(gulp.dest('../resources/static/resources/'))
        .pipe(size());
});

gulp.task('build:resources:prod', gulp.series(
    'clean:resources:all',
    'build:resources:install:node',
    'build:resources',
    'copy:resources:css',
    'copy:resources:js',
    'copy:resources:images',
    'copy:resources:fonts'
));

gulp.task('build:resources:dev', gulp.series(
    'clean:resources:dist',
    'build:resources',
    'copy:resources:css',
    'copy:resources:js',
    'copy:resources:dev'
));

gulp.task('watch:dev', function () {
	gulp.watch('styles/**/*.scss', {delay: 5000}, gulp.series('build:resources:dev'))
});

/*
* Build All
*/
gulp.task('build:all:prod', gulp.series(
    'clean:dist',
    'build:resources:prod'
));

