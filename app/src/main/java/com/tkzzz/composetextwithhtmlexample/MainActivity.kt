package com.tkzzz.composetextwithhtmlexample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
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
//    val string = """<a href="https://hoge.com"> REALITY</a>ggggg"""
    val context = LocalContext.current

    val localUriHandler = LocalUriHandler.current
    val styledAnnotatedString = AnnotatedString.fromHtml(
        htmlString = string,
        linkStyles = TextLinkStyles(
            style = SpanStyle(
                color = Color.Blue,
            )
        ),
        linkInteractionListener = { annotation ->
            Toast.makeText(context,"hoge", Toast.LENGTH_SHORT).show()
            val url = annotation as? LinkAnnotation.Url ?: return@fromHtml
            localUriHandler.openUri(url.url)
        }
    )

    BasicText(
        text = styledAnnotatedString,
//        modifier = Modifier.clickable {
//            Toast.makeText(context,"fuga!", Toast.LENGTH_SHORT).show()
//        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTextWithHtmlExampleTheme {
        TextWithHtml()
    }
}