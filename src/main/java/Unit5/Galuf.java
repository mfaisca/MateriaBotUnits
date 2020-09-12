package Unit5;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Galuf")
public class Galuf extends Unit{
	public Galuf() {
		super("Galuf");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{7344};
				break;
			case HP:
				ids = new int[]{7346};
				break;
			case S1:
				ids = new int[]{7331, 7332};
				break;
			case S2:
				ids = new int[]{7335, 7336};
				break;
			case EX:
				ids = new int[]{7342};
				break;
			case AA:
				ids = new int[]{7354};
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
			case 7331: //S1
			case 7332: //S1+
				ret.getAilmentById(209).setTarget(Ailment.Target.Ally);
				ret.setMastery(7331, 7332, 1);
				Ailment.EffectGrouping eg1 = ret.getAilmentById(209).getEffects().stream().filter(ee -> ee.effectId == 1).findFirst().get();
				eg1.rankData[0] = eg1.rankData[2]; 
				break;
			case 7335: //S2
			case 7336: //S2+
				ret.setMastery(7335, 7336, 1);
				break;
			case 7354: //AA
				ret.fixMissingAuraAilment(1421, 529, null, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}