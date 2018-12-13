package com.gart.base.service

class MockErrorResponse {
    companion object {
        const val data = "{\n" +
                "  \"message\": \"Validation Failed\",\n" +
                "  \"errors\": [\n" +
                "    {\n" +
                "      \"message\": \"None of the search qualifiers apply to this search type.\",\n" +
                "      \"resource\": \"Search\",\n" +
                "      \"field\": \"q\",\n" +
                "      \"code\": \"invalid\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"documentation_url\": \"https://developer.github.com/v3/search/\"\n" +
                "}"
    }
}