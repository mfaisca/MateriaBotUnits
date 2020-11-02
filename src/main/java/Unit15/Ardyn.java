package Unit15;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Ardyn")
public class Ardyn extends Unit{
	public Ardyn() {
		super("Ardyn", "izunia", "adagium", "zannen");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{9535};
				break;
			case HP:
				ids = new int[]{9533, 9537};
				break;
			case S1:
				ids = new int[]{9172, 9537};
				break;
			case S2:
				ids = new int[]{9176, 9537};
				break;
			case EX:
				ids = new int[]{9191, 9537};
				break;
			case AA:
				ids = new int[]{9199};
				break;
			case LD:
				ids = new int[]{9194, 9537};
				break;
			case BT:
				ids = new int[]{9195};
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
		ret.removeHitDataById(10107);
		switch(ret.getId()) {
			case 9172: //S1
			case 9190: //EX+
			case 9191: //EX++
				ret.getAilmentById(1703).setMaxStacks(5);
				ret.getAilmentById(1703).setRank(-1);
				ret.addStaticAilmentEffect(1703, "+1 stack when breaking", 0);
				ret.getAilmentById(1703).setArgs(new int[] {});
				break;
			case 9194: //LD
				ret.getHitDataById(9646).getEffect().setEffectValueType(Ability.Details.Hit_Data.BasedOnStat.Stat7.getId());
				ret.addStaticHit("Sets own HP to 0", 2);
				break;
			case 9537: //Followup
				ret.addStaticHit("Triggers after attacking", 0);
				ret.setName("Zannen");
				break;
			case 9195: //BT
				ret.addStaticAilmentEffect(1702, "+1 stack when breaking", 0);
				ret.fixMissingAuraAilment(1702, 802, Ailment.EffectType.E5, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1702, 692, null, Ailment.Target.Party);
				break;
		}
		return ret;
	}
	
	@Override
	public Passive getSpecificPassive(Passive p) {
		if(p.getLevel() == 50)
			p.setManualDescription("Raises ATK, DEF, Int BRV, Max BRV by 10% when 「**Spectral Charge**」 is at least 3 stacks");
		return p;
	}
}