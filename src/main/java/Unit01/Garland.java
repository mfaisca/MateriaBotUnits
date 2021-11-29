package Unit01;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Ability.BestAbilities;
import com.materiabot.GameElements.Enumerators.Ability.AttackName;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Garland")
public class Garland extends Unit{
	public Garland() { super("Garland"); }
	
	@Override
	public BestAbilities getAbility(AttackName type, Region region) {
		BestAbilities ret = null;
		switch(type) {
			case BRV:
				ret = new BestAbilities(this, 5116, 5116); break;
			case HP:
				ret = new BestAbilities(this, 8793, 8793, 12413); break;
			default:
				break;
		}
		if(ret == null)
			return super.getAbility(type, region);
		if(ret.getAbilities().isEmpty())
			ret.getAbilities().addAll(super.getAbility(type, region).getAbilities());
		return ret;
	}
}