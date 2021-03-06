'use strict';

/**
 * @ngdoc module
 * @name app
 * @description
 *  The main module of the entire application
 *
 *  ## Dependencies
 *
 *  Requires that the following modules be injected in the order they are listed below:
 *  <ul>
 *      <li>{@link ngRoute 'ngRoute'}</li>
 *      <li>{@link ngSanitize 'ngSanitize'}</li>
 *      <li>{@link ngMaterial 'ngMaterial'}</li>
 *      <li>{@link ngMessages 'ngMessages'}</li>
 *      <li>{@link md.data.table 'md.data.table'}</li>
 *      <li>{@link ngAnimate 'ngAnimate'}</li>
 *      <li>{@link appModel 'appModel'}</li>
 *      <li>{@link appService 'appService'</li>
 *      <li>{@link appFilter 'appFilter'}</li>
 *      <li>{@link appDirective 'appDirective'}</li>
 *      <li>{@link appController 'appController'}</li>
 *      <li>{@link scDateTime 'scDateTime'}</li>
 *  </ul>
 */
angular.module('app', [
    'ngRoute',
    'ngSanitize',
    'ngMaterial',
    'ngMessages',
    'md.data.table',
    'ngAnimate',
    'appModel',
    'appService',
    'appFilter',
    'appDirective',
    'appController',
	'scDateTime'
]);