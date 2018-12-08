package com.gart.utils

import com.gart.model.GithubRepositoryLicense
import com.gart.model.GithubRepositoryOwner
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
    fun `return null if stringToLicense is invoked with an empty string`() {
        val license = converter.stringToLicense("")

        assertNull(license)
    }

    @Test
    fun `return a license model if stringToLicense is invoked with a non-null string`() {
        val license = converter.stringToLicense(LICENSE_STRING)

        assertNotNull(license)
    }

    @Test
    fun `return license as a string if licenseToString is invoked with a License model`() {
        val licenseString = converter.licenseToString(GithubRepositoryLicense(0,  LICENSE_KEY, LICENSE_NAME, LICENSE_SPDX_ID, LICENSE_URL, NODE_ID))

        assertNotNull(licenseString)
    }

    @Test
    fun `return null if stringToOwner is invoked with an empty string`() {
        val owner = converter.stringToOwner("")

        assertNull(owner)
    }

    @Test
    fun `return an owner model if stringToOwner is invoked with an non null string`() {
        val owner = converter.stringToOwner(OWNER_STRING)

        assertNotNull(owner)
    }

    @Test
    fun `return owner as a string if ownerToString is invoked with an Owner model`() {
        val owner = converter.ownerToString(GithubRepositoryOwner(0, LOGIN_NAME))

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