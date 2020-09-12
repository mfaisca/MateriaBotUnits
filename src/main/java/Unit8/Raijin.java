package Unit8;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Ailment.EffectGrouping;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Raijin")
public class Raijin extends Unit{
	public Raijin() {
		super("Raijin", "rai-jin", "thunder god");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{8707};
				break;
			case HP:
				ids = new int[]{8711, 8712};
				break;
			case S1:
				ids = new int[]{8700, 8704};
				break;
			case S2:
				ids = new int[]{8702, 8706};
				break;
			case EX:
				ids = new int[]{8710};
				break;
			case AA:
				ids = new int[]{6661};
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
		switch(ret.getId()) {
			case 8704: //S1+
			case 8706: //S2+
				ret.addStaticHit("Enabled when 「**Thunder Mode**」 is active");
				break;
			case 8712: //HP++
				Ability.Details.Hit_Data hd = ret.getHitDataById(6986);
				ret.getDetails().getHits().remove(hd);
				ret.addStaticHit("Enabled when 「**Thunderpeal**」 has 3 stacks", 0);
				ret.getDetails().getHits().add(1, hd);
				break;
			case 6661: //AA
				ret.fixMissingAuraAilment(1286, 409, null, Ailment.Target.Party);
				break;
			case 8702: //S2
				ret.addStaticHit("Enables 「**Thunder Mode**」 for 6 turns");
				break;
			case 8710: //EX
				EffectGrouping eg = ret.getAilmentById(1258).getEffects().remove(1);
				ret.getAilmentById(1258).getEffects().add(0, eg);
				ret.addStaticHit("Enables 「**Thunder Mode**」 for 6 turns");
				ret.fixMissingAuraAilment(1258, 387, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1258, 388, Ailment.EffectType.E7, Ailment.Target.Party);
				ret.getAilmentById(1258).setRank(-1);
				break;
		}
		return ret;
	}
}