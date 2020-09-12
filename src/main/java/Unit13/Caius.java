package Unit13;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Caius")
public class Caius extends Unit{
	public Caius() {
		super("Caius", "ballad", "balad");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{7931};
				break;
			case HP:
				ids = new int[]{7932};
				break;
			case S1:
				ids = new int[]{7155, 7156};
				break;
			case S2:
				ids = new int[]{7163, 7164};
				break;
			case EX:
				ids = new int[]{7786, 7787};
				break;
			case AA:
				ids = new int[]{7796};
				break;
			case LD:
				ids = new int[]{11119};
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
		ret.removeHitDataById(7713);
		switch(ret.getId()) {
			case 7787: //EX+
				ret.fixDelayHitData(5126);
				ret.getAilmentById(1480).getEffects().get(0).effectId = Ailment.EffectType.E140.getId();
				ret.getAilmentById(1480).getEffects().get(1).effectId = Ailment.EffectType.E4.getId();
				ret.getAilmentById(1480).getEffects().get(2).effectId = Ailment.EffectType.E5.getId();
			case 7156: //S1+
			case 7164: //S2+
				ret.getAilmentById(1397).setRank(-1);
				ret.addStaticHit("Enabled when 「**" + super.getSpecificAilment(1397).getName() + "**」 has 5 stacks", 0);
				break;
			case 7786: //EX
				ret.fixDelayHitData(5126);
				ret.getAilmentById(1480).getEffects().get(0).effectId = Ailment.EffectType.E140.getId();
				ret.getAilmentById(1480).getEffects().get(1).effectId = Ailment.EffectType.E4.getId();
				ret.getAilmentById(1480).getEffects().get(2).effectId = Ailment.EffectType.E5.getId();
			case 7155: //S1
				ret.getAilmentById(1397).setRank(-1);
				break;
		}
		return ret;
	}
}