import "./styles/landing.scss";
import "./styles/search.scss";

var $ = require("jquery");
var bootstrap = require("bootstrap");

window.$ = $;
window.jQuery = $;

require( "datatables.net" ) (window, $);
require( "datatables.net-bs4" ) (window, $);
