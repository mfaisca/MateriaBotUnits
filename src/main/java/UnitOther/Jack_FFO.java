package UnitOther;
import com.materiabot.GameElements.*;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Jack")
public class Jack_FFO extends Unit{
	public Jack_FFO() { super("Jack Garland"); }
	
	@Override
	public String getName() {
		return "Jack Garland";
	}
	
	@Override
	public String getPluginName() {
		return this.getClass().getSimpleName();
	}
}