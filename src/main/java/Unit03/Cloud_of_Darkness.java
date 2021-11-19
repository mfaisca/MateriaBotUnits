package Unit03;
import com.materiabot.GameElements.*;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Cloud_of_Darkness")
public class Cloud_of_Darkness extends Unit{
	public Cloud_of_Darkness() { super("Cloud of Darkness"); }

	@Override
	public void loadFixGL() {
		for(Ability a : this.getAbilities().values())
			switch(a.getId()) {
				case 7659:
					a.getHitData().add(new HitData(a, "Doesn't consume a skill use")); break;
			}
	}
	@Override
	public void loadFixJP() {
		loadFixGL();
	}
}