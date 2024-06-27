package com.tkzzz.composetextwithhtmlexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.tooling.preview.Preview
import com.tkzzz.composetextwithhtmlexample.ui.theme.ComposeTextWithHtmlExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTextWithHtmlExampleTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding()
                ) { innerPadding ->
                    TextWithHtml(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TextWithHtml(modifier: Modifier = Modifier) {
//    val string = """<a href="https://reality.app"> REALITY </a> へようこそ！！！！！！<br>123456<big><font color="RED">7890</font></big>1234567890""" // resourceを経由しないならHTMLエスケープはしなくてよい
//    val string = stringResource(id = R.string.tagged_text) // こっちはHtmlエスケープしてないリソースなので反映されない。
    val string = stringResource(id = R.string.tagged_text_with_escape)
    val styledAnnotatedString = AnnotatedString.fromHtml(htmlString = string)


    Text(
        text = styledAnnotatedString,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTextWithHtmlExampleTheme {
        TextWithHtml()
    }
}