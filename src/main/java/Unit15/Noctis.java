package Unit15;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Ability.BestAbilities;
import com.materiabot.GameElements.Enumerators.Ability.AttackName;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Noctis")
public class Noctis extends Unit{
	public Noctis() { super("Noctis"); }
	
	@Override
	public BestAbilities getAbility(AttackName type, Region region) {
		BestAbilities ret = null;
		switch(type) {
			case HP:
				ret = new BestAbilities(this, 9333, 4327, 9333, 9451);
				break;
			case S1:
				ret = new BestAbilities(this, 15401, 15400, 15401, 9451);
				break;
			case S2:
				ret = new BestAbilities(this, 15407, 15406, 15407, 9451);
				break;
			case EX:
				ret = new BestAbilities(this, 15411, 15410, 15411);
				break;
			case AA:
				ret = new BestAbilities(this, 15418, 15418);
				break;
			case LD:
				ret = new BestAbilities(this, 13347, 13347, 9451);
				break;
			case BT:
				ret = new BestAbilities(this, 15415, 9285, 15415, 9217);
				break;
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
		this.getSpecificAbility(4560).setName(super.getSpecificAbility(9450).getName());
		this.getUpgradedAbilities().removeIf(ua -> ua.getId() == 87);
	}
}