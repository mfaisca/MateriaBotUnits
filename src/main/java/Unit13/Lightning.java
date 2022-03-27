package Unit13;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Ability.BestAbilities;
import com.materiabot.GameElements.Enumerators.Ability.AttackName;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Lightning")
public class Lightning extends Unit{
	public Lightning() { super("Lightning"); }
	
	@Override
	public BestAbilities getAbility(AttackName type, Region region) {
		BestAbilities ret = null;
		switch(type) {
			case BRV:
				ret = new BestAbilities(this, 4541, 4541, 4542);
				break;
			case HP:
				ret = new BestAbilities(this, 7877, 7877, 7878);
				break;
			case S1:
				ret = new BestAbilities(this, 14731, 14730, 14731);
				break;
			case S2:
				ret = new BestAbilities(this, 14737, 14736, 14737);
				break;
			case EX:
				ret = new BestAbilities(this, 15878, 15877, 15878);
				break;
			case AA:
				ret = new BestAbilities(this, 15418, 15418);
				break;
			case LD:
				ret = new BestAbilities(this, 14738);
				break;
			case BT:
				ret = new BestAbilities(this, 14740, 9625, 14740);
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
	public Ailment getSpecificAilment(Integer id) {
		Ailment ail = super.getSpecificAilment(id);
		if(ail.getId() == 642) { //Siphon
			ail.setRank(0);
			ail.getEffects()[1] = 119;
			ail.getEffects()[2] = 120;
		}
		return ail;
	}
	
	@Override
	public void loadFixGL() {
		this.getAbilities().values().stream()
			.filter(a -> a.getId() == 14738)
			.flatMap(a -> a.getAilments().stream())
			.filter(a -> a.getId() == 642)
			.forEach(a -> {
				a.setRank(0);
				a.getEffects()[1] = 119;
				a.getEffects()[2] = 120;
			});
	}
}