Feature: Simple Books API Testing

Scenario: Retrieve list of books
   Given Simple Books API is available
   When I send a GET request to "/books"
   Then The response status code should be 200
   Then The response for the field "type" should contain value "fiction" for bookId 4

   Scenario Outline: To verify Post API to create an order
      Given I have an auth token
      When  To submit a new order using post API
      Then The response status code should be 201
      Then verify the field "created" should have value "true"

      Examples:
      |field|value|
      |created|true|

Scenario: For updating the data using Patch API
      Given Simple Books API is available
      Given I have an auth token
      When I update an order with orderId "gqKOcxBBFJkYQN8bP-kiq"









