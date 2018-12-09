package com.gart.data.utils

import com.gart.data.model.GithubRepositoryItem
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class ConverterTest {

    private lateinit var converter: Converter

    @Before
    fun setUp() {
        converter = Converter()
    }

    @Test
    fun return_null_if_stringToLicense_is_invoked_with_an_empty_string() {
        val license = converter.stringToLicense("")

        assertNull(license)
    }

    @Test
    fun return_a_license_model_if_stringToLicense_is_invoked_with_a_non_null_string() {
        val license = converter.stringToLicense(LICENSE_STRING)

        assertNotNull(license)
    }

    @Test
    fun return_license_as_a_string_if_licenseToString_is_invoked_with_a_License_model() {
        val licenseString = converter.licenseToString(GithubRepositoryItem.License(1,  LICENSE_KEY, LICENSE_NAME, LICENSE_SPDX_ID, LICENSE_URL, NODE_ID, 1))

        assertNotNull(licenseString)
    }

    @Test
    fun return_null_if_stringToOwner_is_invoked_with_an_empty_string() {
        val owner = converter.stringToOwner("")

        assertNull(owner)
    }

    @Test
    fun return_an_owner_model_if_stringToOwner_is_invoked_with_an_non_null_string() {
        val owner = converter.stringToOwner(OWNER_STRING)

        assertNotNull(owner)
    }

    @Test
    fun return_owner_as_a_string_if_ownerToString_is_invoked_with_an_Owner_model() {
        val owner = converter.ownerToString(GithubRepositoryItem.Owner(1, LOGIN_NAME, 1))

        assertNotNull(owner)
    }

    companion object {
        private const val LICENSE_KEY = "apache-2.0"
        private const val LICENSE_NAME = "Apache License 2.0"
        private const val LICENSE_SPDX_ID = "Apache-2.0"
        private const val LICENSE_URL = "https://api.github.com/licenses/apache-2.0"
        private const val NODE_ID = "MDc6TGljZW5zZTI="
        private const val LOGIN_NAME = "NalediMadlopha"

        private const val LICENSE_STRING = "{\n" +
                "        \"key\": \"$LICENSE_KEY\",\n" +
                "        \"name\": \"$LICENSE_NAME\",\n" +
                "        \"spdx_id\": \"$LICENSE_SPDX_ID\",\n" +
                "        \"url\": \"$LICENSE_URL\",\n" +
                "        \"node_id\": \"$NODE_ID\"\n" +
                "      }"

        private const val OWNER_STRING = "{\n" +
                "        \"login\": \"PhilJay\"" +
                "      }"
    }

}