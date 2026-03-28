import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.yusubov.composebook.core.knobs.defaults.BooleanKnob

@Preview()
@Composable
fun GreetingPreview() {
    val knob = BooleanKnob("isEnabled", initialValue = true)

    MaterialTheme {
        knob.Content(knob.value) {
            knob.update(it)
        }
    }
}