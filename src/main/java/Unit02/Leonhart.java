package Unit02;
import com.materiabot.GameElements.*;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Leonhart")
public class Leonhart extends Unit{
	public Leonhart() { super("Leon"); }
	
	@Override
	public String getName() {
		return "Leon";
	}
	
	@Override
	public String getPluginName() {
		return this.getClass().getSimpleName();
	}
}