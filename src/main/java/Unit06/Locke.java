package Unit06;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Ability.BestAbilities;
import com.materiabot.GameElements.Enumerators.Ability.AttackName;
import com.materiabot.IO.JSON.UnitParser;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Locke")
public class Locke extends Unit{
	public Locke() { super("Locke"); }
	
	@Override
	public BestAbilities getAbility(AttackName type, Region region) {
		BestAbilities ret = null;
		switch(type) {
			case BRV:
				ret = new BestAbilities(this, 4710, 4710, 15386); break;
			case HP:
				ret = new BestAbilities(this, 10028, 10027, 10028); break;
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
		this.getSpecificAilment(2745).getAuras().stream().filter(a -> a.getId() == 1237).forEach(a -> a.getRankData()[0] = 330);
		this.getSpecificAilment(2797).getAuras().stream().filter(a -> a.getId() == 1273).forEach(a -> a.getRankData()[0] = 330);
	}
	
	@Override
	public void loadFixJP() {
		loadFixGL();
		UnitParser.fixBT(this, 17137, 200, 400, 60);
	}
}