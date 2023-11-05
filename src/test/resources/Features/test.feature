Feature: Search Feature
  Verify the search functionality

  @searchItem
  Scenario Outline: User is able to Search for an Item
    Given Open the Browser
    And Enter the given URL
    When User Search for the keyword <keyword>
    Then search results page is opened for the auctions <keyword>
    When User selects the second lot from the list <keyword>
    Then Validate the selected Lot page is opened
    And Print the values of Lot's name, favourites counter and current bid
    Examples:
      | keyword |
      | "train" |
