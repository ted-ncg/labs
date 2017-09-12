# More Negative Tests

### https://github.com/ted-ncg/labs/blob/master/03-negative-testing.md

## Goal

Add more "negative" tests to the Account class testing.

## Steps

1. Download (or `git pull`) the latest code from `https://github.com/ted-ncg/foster-city-canteen`

1. Make sure all of the tests currently pass!

1. Add new code (test first!) that ensures amounts for withdraw are positive, i.e., don't allow withdrawing negative amounts.

    * If not valid, throw an appropriate `Exception`
    
    * Remember to follow the *Red-Green-Commit-Refactor-Commit* cycle

1. Do the same thing for `deposit`, ensuring that the amounts are valid.
