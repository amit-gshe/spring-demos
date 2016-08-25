Feature: Query user by name
    Scenario: Query a existing user
        Given a user with name Lily
        And this user Lily exists
        Then Lily's info should be returned
