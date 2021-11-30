package Unit01;
import com.materiabot.GameElements.*;
import com.materiabot.IO.JSON.UnitParser;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Garland")
public class Garland extends Unit{
	public Garland() { super("Garland"); }

	@Override
	public void loadFixGL() {
		UnitParser.fixBT(this, 17137, null, 400, null);
	}
	@Override
	public void loadFixJP() {
		loadFixGL();
	}
}