import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.yusubov.composebook.core.knobs.defaults.BooleanKnob
import com.yusubov.composebook.core.knobs.nullable

@Preview()
@Composable
fun GreetingPreview() {
    val knob = BooleanKnob("isEnabled", initialValue = true).nullable()

    MaterialTheme {
        knob.Content(knob.value) {
            knob.update(it)
        }
    }
}