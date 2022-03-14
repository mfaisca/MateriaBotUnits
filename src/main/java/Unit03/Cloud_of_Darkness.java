package Unit03;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Ability.BestAbilities;
import com.materiabot.GameElements.Enumerators.Ability.AttackName;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Cloud_of_Darkness")
public class Cloud_of_Darkness extends Unit{
	public Cloud_of_Darkness() { super("Cloud of Darkness"); }
	
	@Override
	public BestAbilities getAbility(AttackName type, Region region) {
		BestAbilities ret = null;
		switch(type) {
			case S2:
				ret = new BestAbilities(this, 7659, 7658, 7659); break;
			default:
				break;
		}
		if(ret == null)
			return super.getAbility(type, region);
		if(ret.getAbilities().isEmpty())
			ret.getAbilities().addAll(super.getAbility(type, region).getAbilities());
		return ret;
	}

	@Override
	public void loadFixGL() {
		for(Ability a : this.getAbilities().values())
			switch(a.getId()) {
				case 7659:
					a.getHitData().add(new HitData(a, "Doesn't consume a skill use")); break;
			}
	}
}