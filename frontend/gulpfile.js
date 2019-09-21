var gulp = require('gulp');
var del = require('del');
var concat = require('gulp-concat');
var exec = require('gulp-exec');
var size = require('gulp-size');
var watch = require('gulp-watch');

/*
* Clean
*/
gulp.task('clean:dist', function () {

    return del(['dist/**/*']);
});

gulp.task('clean:resources:dist', function() {

    return del(['styles/dist/**/*']);
});

gulp.task('clean:final:dist', function() {

    return del(['../src/main/resources/static/resources/**/*']);
})

/*
* Webpack
*/
gulp.task('webpack:build', function() {

    return gulp.src('./styles')
        .pipe(exec('cd styles && npm run build'));
});

/*
* Copy
*/
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

gulp.task('copy:resources:final', function() {

    return gulp.src(['./dist/**/*',])
        .pipe(gulp.dest('../src/main/resources/static/resources'))
        .pipe(size());
});

/*
* Build All
*/
gulp.task('build', gulp.series(
    gulp.parallel (
        'clean:dist',
        'clean:resources:dist',
        'clean:final:dist'
    ),
    'webpack:build',
    gulp.parallel(
        'copy:resources:css',
        'copy:resources:images',
        'copy:resources:fonts',
        'copy:resources:js',
    ),
    'copy:resources:final'
));

/*
* Watch
*/
gulp.task('watch:dev', function () {
	gulp.watch('styles/**/*.scss', {delay: 5000}, gulp.series('build'));
});
