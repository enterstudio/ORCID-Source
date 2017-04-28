var webpack = require('webpack');

module.exports = {
    entry: "./require.js",
    module: {
        loaders: [
            //{ test: /\.js$/, loader: 'babel', exclude: [/node_modules/] },
            //{ test: /\.html$/, loader: 'html-loader', exclude: [/node_modules/] },
            //{ test: /\.css$/, loader: "style!css", exclude: [/node_modules/] },
            //{ test: /\.scss$/, loader: "style!css!sass", exclude: [/node_modules/] }
            { 
                test: /\.ts$/, 
                loader: 'ts-loader' 
            }
        ]
    },
    output: {
        //library: "orcid",
        //libraryTarget: "var",
        path: __dirname,
        filename: "../angular_orcid_generated.js"
    },
    //Uglify won't work with the way things are declared at this moment
    plugins: [
        /*new webpack.optimize.UglifyJsPlugin(
            {
                compress: { 
                   warnings: false 
                },
                minimize: true
            }
        )*/
        /*new ngtools.AotPlugin(
            {
                tsConfigPath: './tsconfig.json'
            }
        )*/
    ],
    resolve: {
      //  extensions: ['.ts', '.js'],
        alias: {
            "@angular/upgrade/static": "@angular/upgrade/bundles/upgrade-static.umd.js"
        }
    },
    scripts: {
        build: "webpack -d",
        //prebuild: "npm install",
        watch: "webpack --watch -d &"
    },
    //watch: true
};