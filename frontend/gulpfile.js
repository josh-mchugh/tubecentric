var gulp = require('gulp');
var del = require('del');
var concat = require('gulp-concat');
var exec = require('gulp-exec');
var size = require('gulp-size');
var watch = require('gulp-watch');
var debug = require('gulp-debug');
var webpackStream = require('webpack-stream');
var webpack = require('webpack');

/*
* Clean
*/
gulp.task('clean:final:dist', function() {

    return del(['../src/main/resources/static/resources/**/*'], {force: true});
});

/*
* Webpack
*/
gulp.task('webpack:build', function() {

    return gulp.src('./src')
        .pipe(debug())
        .pipe(webpackStream(require('./webpack.prod.js'), webpack)
            .on('error', function (err) {
                console.log(err);
                this.emit('end');
            }))
        .pipe(gulp.dest('./dist'))
});

/*
* Copy
*/
gulp.task('copy:resources:css', function() {

    return gulp.src('dist/**.css')
        .pipe(gulp.dest('../src/main/resources/static/resources/styles'))
        .pipe(size());
});

gulp.task('copy:resources:images', function () {

    return gulp.src('src/resources/new-images/**.*')
        .pipe(gulp.dest('../src/main/resources/static/resources/images'))
        .pipe(size());
});

gulp.task('copy:resources:fonts', function() {

    return gulp.src('node_modules/@fortawesome/fontawesome-free/webfonts/**.*')
        .pipe(gulp.dest('../src/main/resources/static/resources/fonts'))
        .pipe(size());
});

gulp.task('copy:resources:js', function() {

    return gulp.src('dist/**.js')
        .pipe(gulp.dest('../src/main/resources/static/resources/vendor/'))
        .pipe(size());
});

/*
* Build All
*/
gulp.task('build', gulp.series(
    gulp.parallel(
        'clean:final:dist',
        'webpack:build'
    ),
    gulp.parallel(
        'copy:resources:css',
        'copy:resources:images',
        'copy:resources:fonts',
        'copy:resources:js',
    )
));

/*
* Watch
*/
gulp.task('watch:dev', function () {
	gulp.watch('styles/**/*.scss', {delay: 5000}, gulp.series('build'));
});
