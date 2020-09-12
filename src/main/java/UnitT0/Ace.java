package UnitT0;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Ace")
public class Ace extends Unit{
	public Ace() {
		super("Ace", "acey");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{4894};
				break;
			case HP:
				ids = new int[]{4895};
				break;
			case S1:
				ids = new int[]{4723, 4724};
				break;
			case S2:
				ids = new int[]{4730, 4731};
				break;
			case EX:
				ids = new int[]{5207};
				break;
			case AA:
				ids = new int[]{4693};
				break;
			case LD:
				ids = new int[]{};
				break;
			case BT:
				ids = new int[]{};
				break;
			case CA:
				ids = new int[]{};
				break;
		}
		if(ids.length == 0)
			return super.getAbility(type, region);
		else
			return IntStream.of(ids).boxed().map(i -> this.getSpecificAbility(i)).collect(Collectors.toList());
	}
	
	@Override
	public Ability getSpecificAbility(int id){
		Ability ret = super.getSpecificAbility(id);
		Ailment cardStock = ret.getAilmentById(356);
		if(cardStock != null)
			cardStock.setName("Card Stock");
		switch(ret.getId()) {
			case 5207:
				ret.fixMissingAuraAilment(650, 5, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(653, 8, null, Ailment.Target.AoE);
				ret.fixMissingAuraAilment(656, 11, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(656, 14, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(659, 17, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(659, 20, null, Ailment.Target.Party);
				for(int i = 0; i < 4; i++) {
					ret.getAilmentById(650 + (i * 3)).setRank(0);
					ret.getAilmentById(650 + (i * 3)).removeEffect(5);
					ret.getAilmentById(650 + (i * 3)).getEffects().add(new Ailment.EffectGrouping(60));
				}
				break;
		}
		return ret;
	}
}