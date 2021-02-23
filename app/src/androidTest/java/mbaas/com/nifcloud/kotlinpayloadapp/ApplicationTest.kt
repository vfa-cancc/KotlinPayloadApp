package mbaas.com.nifcloud.kotlinpayloadapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.Matchers.*

@RunWith(AndroidJUnit4ClassRunner::class)
class ApplicationTest {
    @get:Rule
    var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun init() {
        // Specify a valid string.
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("mbaas.com.nifcloud.kotlinpayloadapp", appContext.packageName)
    }

    @Test
    fun validateDisplayScreen() {
        onView(allOf(withText("KotlinPayloadApp"), isDisplayed()))
        onView(allOf(withText("com.nifcloud.mbaas.pushId"), isDisplayed()))
        onView(allOf(withText("com.nifcloud.mbaas.richUrl"), isDisplayed()))
        onView(allOf(withText("data (JSON)"), isDisplayed()))
        onView(allOf(withId(R.id.txtPushid), withText("")))
        onView(allOf(withId(R.id.txtRichurl), withText("")))
        onData(allOf(`is`(instanceOf(String.javaClass)), `is`("Key: No key"))).inAdapterView(withId(R.id.lsJson))
    }
}