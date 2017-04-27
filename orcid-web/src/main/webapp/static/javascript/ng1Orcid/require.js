//npm install -g typescript

function requireAll(requireContext) {
  return requireContext.keys().map(requireContext);
}

require('./app/angularOrcidOriginal.js');
//require('./app/main.ts');
requireAll(require.context("./app/controllers", true, /^\.\/.*\.js$/));
requireAll(require.context("./app/controllers", true, /^\.\/.*\.ts$/));
requireAll(require.context("./app/directives", true, /^\.\/.*\.js$/));
requireAll(require.context("./app/directives", true, /^\.\/.*\.ts$/));
requireAll(require.context("./app/modules", true, /^\.\/.*\.js$/));
//requireAll(require.context("./app/modules", true, /^\.\/.*\.ts$/));
requireAll(require.context("./app/services", true, /^\.\/.*\.js$/));
//requireAll(require.context("./app/services", true, /^\.\/.*\.ts$/));