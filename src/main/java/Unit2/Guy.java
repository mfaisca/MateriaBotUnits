package Unit2;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import com.materiabot.GameElements.Ailment.Target;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Guy")
public class Guy extends Unit{
	public Guy() {
		super("Guy");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{11003};
				break;
			case HP:
				ids = new int[]{11004};
				break;
			case S1:
				ids = new int[]{10420};
				break;
			case S2:
				ids = new int[]{10424};
				break;
			case EX:
				ids = new int[]{10428};
				break;
			case AA:
				ids = new int[]{10434};
				break;
			case LD:
				ids = new int[]{11000};
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
		Ability ret = super.getSpecificAbility(id); //
		switch(ret.getId()) {
			case 10424:
				ret.fixMissingAuraAilment(1705, 888, Ailment.EffectType.E5, Target.Allies);
				ret.getAilmentById(1705).setRank(-1);
				break;
			case 10434:
				ret.fixMissingAuraAilment(1980, 952, Ailment.EffectType.E97, Ailment.Target.Party);
				ret.getAilmentById(1705).getAuras().remove(953);
				break;
			case 10428:
				ret.fixMissingAuraAilment(1978, 948, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1978, 890, Ailment.EffectType.E1, Ailment.Target.Party);
				break;
			case 11000:
				Ailment a = ret.removeTemporaryEffect(2131);
				if(a != null) {
					String effect = Ailment.EffectType.get(a.getEffects().get(0).effectId).getBaseDescription();
					effect = effect.replace("{0}", "+"+a.getEffects().get(0).rankData[a.getRank()]);
					effect = effect.replace("{t}", "");
					ret.addStaticHit(effect);
				}
				ret.fixMissingAuraAilment(1979, 949, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1979, 950, Ailment.EffectType.E2, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1979, 891, null, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}