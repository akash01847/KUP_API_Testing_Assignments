const assert = require('chai').assert;
const addNumbers = require('./math').addNumbers;

describe('addNumbers', function() {
  it('should return the sum of two numbers', function() {
    assert.equal(addNumbers(2, 3), 5);
    assert.equal(addNumbers(0, 0), 0);
    assert.equal(addNumbers(-2, 2), 0);
  });
});