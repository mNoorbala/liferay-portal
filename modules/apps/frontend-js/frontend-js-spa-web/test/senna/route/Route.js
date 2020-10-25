/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

'use strict';

import {core} from 'metal';

import Route from '../../src/route/Route';

describe('Route', () => {
	describe('Constructor', () => {
		it('should throws error when path and handler not specified', () => {
			assert.throws(() => {
				new Route();
			}, Error);
		});

		it('should throws error when path is null', () => {
			assert.throws(() => {
				new Route(null, core.nullFunction);
			}, Error);
		});

		it('should throws error when path is undefined', () => {
			assert.throws(() => {
				new Route(undefined, core.nullFunction);
			}, Error);
		});

		it('should throws error when handler not specified', () => {
			assert.throws(() => {
				new Route('/path');
			}, Error);
		});

		it('should throws error when handler not a function', () => {
			assert.throws(() => {
				new Route('/path', {});
			}, Error);
		});

		it('should not throws error when handler is a function', () => {
			assert.doesNotThrow(() => {
				new Route('/path', core.nullFunction);
			});
		});

		it('should set path and handler from constructor', () => {
			var route = new Route('/path', core.nullFunction);
			assert.strictEqual('/path', route.getPath());
			assert.strictEqual(core.nullFunction, route.getHandler());
		});
	});

	describe('Matching', () => {
		it('should match route by string path', () => {
			var route = new Route('/path', core.nullFunction);
			assert.ok(route.matchesPath('/path'));
		});

		it('should match route by string path with params', () => {
			var route = new Route('/path/:foo(\\d+)', core.nullFunction);
			assert.ok(route.matchesPath('/path/10'));
			assert.ok(route.matchesPath('/path/10/'));
			assert.ok(!route.matchesPath('/path/abc'));
			assert.ok(!route.matchesPath('/path'));
		});

		it('should match route by regex path', () => {
			var route = new Route(/\/path/, core.nullFunction);
			assert.ok(route.matchesPath('/path'));
		});

		it('should match route by function path', () => {
			var route = new Route((path) => {
				return path === '/path';
			}, core.nullFunction);
			assert.ok(route.matchesPath('/path'));
		});

		it('should not match any route', () => {
			var route = new Route('/path', core.nullFunction);
			assert.ok(!route.matchesPath('/invalid'));
		});

		it('should not match any route for invalid path', () => {
			var route = new Route({}, core.nullFunction);
			assert.ok(!route.matchesPath('/invalid'));
		});
	});

	describe('Extracting params', () => {
		it('should extract params from path matching route', () => {
			var route = new Route(
				'/path/:foo(\\d+)/:bar(\\w+)',
				core.nullFunction
			);
			var params = route.extractParams('/path/123/abc');
			var expected = {
				foo: '123',
				bar: 'abc',
			};
			assert.deepEqual(expected, params);
		});

		it('should return null if try to extract params from non matching route', () => {
			var route = new Route(
				'/path/:foo(\\d+)/:bar(\\w+)',
				core.nullFunction
			);
			var params = route.extractParams('/path/abc/123');
			assert.strictEqual(null, params);
		});

		it('should return empty object if trying to extract params from path given as function', () => {
			var route = new Route(core.nullFunction, core.nullFunction);
			var params = route.extractParams('/path/123/abc');
			assert.deepEqual({}, params);
		});
	});
});
