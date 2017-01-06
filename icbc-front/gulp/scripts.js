'use strict';

var path = require('path');
var gulp = require('gulp');
var conf = require('./conf');
var fs = require('fs');

var browserSync = require('browser-sync');
var insert = require('gulp-insert');

var $ = require('gulp-load-plugins')();

function webpack(watch, callback) {
  var webpackOptions = {
    watch: watch,
    module: {
      preLoaders: [{ test: /\.js$/, exclude: /node_modules/, loader: 'jshint-loader'}],
      loaders: [{ test: /\.js$/, exclude: /node_modules/, loader: 'babel-loader'}]
    },
    output: { filename: 'index.js' }
  };

  if(watch) {
    webpackOptions.devtool = 'inline-source-map';
  }

  var webpackChangeHandler = function(err, stats) {
    if(err) {
      conf.errorHandler('Webpack')(err);
    }
    $.util.log(stats.toString({
      colors: $.util.colors.supportsColor,
      chunks: false,
      hash: false,
      version: false
    }));
    browserSync.reload();
    if(watch) {
      watch = false;
      callback();
    }
  };

  return gulp.src(path.join(conf.paths.src, '/app/index.js'))
    .pipe($.webpack(webpackOptions, null, webpackChangeHandler))
    .pipe(gulp.dest(path.join(conf.paths.tmp, '/serve/app')));
}

gulp.task('scripts', function () {
  return webpack(false);
});

gulp.task('scripts:watch', ['scripts'], function (callback) {
  return webpack(true, callback);
});

function getFolders(dir) {
  return fs.readdirSync(dir)
    .filter(function(file) {
      return fs.statSync(path.join(dir, file)).isDirectory();
    });
}

gulp.task('insertWords', function() {
  var keyWords = '你的代码优不优雅?';
  var words = '/**\n' +
              '* ' + keyWords + '\n' +
              '* Each engineer has a duty to keep the code elegant\n' +
              '*\n' +
              '*/\n' +
              '\n';

  var folders = getFolders(conf.paths.app);
  return folders.map(function(folder) {
    gulp.src([
      path.join(conf.paths.app, folder, '/**/*.js')
    ])
    .pipe(insert.transform(function(contents, file) {
      if (contents.indexOf(keyWords) === -1) {
        contents = words + contents;
      }
      return contents;
    }))
    .pipe(gulp.dest(conf.paths.app + '/' + folder));
  });
});
