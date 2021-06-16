package Unit8;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Quistis")
public class Quistis extends Unit{
	public Quistis() {
		super("Quistis", "trepe");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{8844};
				break;
			case HP:
				ids = new int[]{8845};
				break;
			case S1:
				ids = new int[]{8725};
				break;
			case S2:
				ids = new int[]{8727};
				break;
			case EX:
				ids = new int[]{8729};
				break;
			case AA:
				ids = new int[]{7060};
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
			case 8844: //BRV
			case 8845: //HP
				ret.addStaticHit("Enabled for 6 turns after using ?**Laser Whip**」");
				break;
			case 8729:
				ret.fixMissingAuraAilment(1637, 673, Ailment.EffectType.E67, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1637, 665, null, Ailment.Target.Party);
				break;
			case 7060: //AA
				ret.fixMissingAuraAilment(1381, 452, Ailment.EffectType.E67, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}