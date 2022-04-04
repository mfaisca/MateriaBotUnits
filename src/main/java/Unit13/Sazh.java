package Unit13;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Ability.BestAbilities;
import com.materiabot.GameElements.Enumerators.Ability.AttackName;
import java.util.stream.Collectors;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Sazh")
public class Sazh extends Unit{
	public Sazh() { super("Sazh"); }
	
	@Override
	public BestAbilities getAbility(AttackName type, Region region) {
		BestAbilities ret = null;
		switch(type) {
			case BRV:
				ret = new BestAbilities(this, 14997);
				break;
			case HP:
				ret = new BestAbilities(this, 14998);
				break;
			case S2:
				ret = new BestAbilities(this, 14992);
				break;
			case EX:
				switch(region) {
					case GL:
						ret = new BestAbilities(this, 8641); break;
					case JP:
						ret = new BestAbilities(this, 16192); break;
					default:
						ret = new BestAbilities(this, 8641); break;
				}
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
	public void loadFixGL() {
		this.getAbilities().values().stream()
			.filter(a -> a.getId() == 14992)
			.forEach(a -> 
				a.setAilments(a.getAilments().stream().filter(aa -> aa.isVisible()).collect(Collectors.toList())));
	}
}