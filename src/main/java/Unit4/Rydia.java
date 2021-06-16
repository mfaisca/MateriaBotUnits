package Unit4;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Rydia")
public class Rydia extends Unit{
	public Rydia() {
		super("Rydia");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{8754};
				break;
			case HP:
				ids = new int[]{8755};
				break;
			case S1:
				ids = new int[]{8748};
				break;
			case S2:
				ids = new int[]{8750};
				break;
			case EX:
				ids = new int[]{8753};
				break;
			case AA:
				ids = new int[]{5366};
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
			case 8753: //EX
				ret.fixMissingAuraAilment(1628, 659, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1628, 660, Ailment.EffectType.E5, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1628, 671, null, Ailment.Target.Party);
				break;
			case 5366:
				Ailment.Aura a = ret.fixMissingAuraAilment(1113, 297, Ailment.EffectType.E67, Ailment.Target.Party);
				a.rankData[1] = a.rankData[0];
				ret.fixMissingAuraAilment(1113, 299, Ailment.EffectType.E97, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}