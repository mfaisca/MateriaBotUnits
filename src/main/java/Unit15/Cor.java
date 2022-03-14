package Unit15;
import com.materiabot.GameElements.*;
import com.materiabot.IO.JSON.UnitParser;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Cor")
public class Cor extends Unit{
	public Cor() { super("Cor"); }
	
	@Override
	public void loadFixGL() {
		//S1 Fix
		UnitParser.merge(this, 14848, 14880);
		this.getTriggeredAbilities().remove(this.getTriggeredAbilities().stream().filter(ta -> ta.getId() == 37).findFirst().orElse(null));
		//S2 Fix
		UnitParser.merge(this, 14852, 14884);
		UnitParser.merge(this, 14852, 15600);
		this.getTriggeredAbilities().remove(this.getTriggeredAbilities().stream().filter(ta -> ta.getId() == 41).findFirst().orElse(null));
		this.getTriggeredAbilities().remove(this.getTriggeredAbilities().stream().filter(ta -> ta.getId() == 51).findFirst().orElse(null));
		this.getTriggeredAbilities().remove(this.getTriggeredAbilities().stream().filter(ta -> ta.getId() == 61).findFirst().orElse(null));
		this.getAbilities().values().stream().flatMap(a -> a.getAilments().stream()).filter(a -> a.getRank() == -1).forEach(a -> a.setRank(0));
	}
}