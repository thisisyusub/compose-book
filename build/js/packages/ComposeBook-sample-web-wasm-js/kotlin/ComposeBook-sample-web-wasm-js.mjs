
import * as Li9za2lrby5tanM from './skiko.mjs';
import * as _ref_QGpzLWpvZGEvY29yZQ_ from '@js-joda/core';
import { instantiate } from './ComposeBook-sample-web-wasm-js.uninstantiated.mjs';
import "./custom-formatters.js"

const exports = (await instantiate({
    './skiko.mjs': Li9za2lrby5tanM,
    '@js-joda/core': _ref_QGpzLWpvZGEvY29yZQ_
})).exports;

export const {
memory,
_initialize
} = exports


