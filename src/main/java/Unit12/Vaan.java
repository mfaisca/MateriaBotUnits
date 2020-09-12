package Unit12;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Vaan")
public class Vaan extends Unit{
	public Vaan() {
		super("Vaan", "captain basch", "captain basch fon ronsenburg", "captain basch fon ronsenburg of dalmasca");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{4987};
				break;
			case HP:
				ids = new int[]{5832};
				break;
			case S1:
				ids = new int[]{4973, 4970, 4975, 4972};
				break;
			case S2:
				ids = new int[]{4976, 4983, 4979, 4985};
				break;
			case EX:
				ids = new int[]{5833};
				break;
			case AA:
				ids = new int[]{5698};
				break;
			case LD:
				ids = new int[]{9112};
				break;
			case BT:
				ids = new int[]{9113};
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
			case 4973: //S1
                ret.addStaticHit("Trigger 「**" + super.getSpecificAbility(4970).getName() + "**」 on target after using this ability once", 1);
                break;
			case 4975: //S1+
                ret.addStaticHit("Enabled after using this ability 5 times", 1);
                ret.addStaticHit("Trigger 「**" + super.getSpecificAbility(4972).getName() + "**」 on target after using this ability once", 1);
                break;
			case 4976: //S2
                ret.addStaticHit("Trigger 「**" + super.getSpecificAbility(4983).getName() + "**」 on target after using this ability once", 1);
                break;
			case 4979: //S2+
                ret.addStaticHit("Enabled after using this ability 5 times", 1);
                ret.addStaticHit("Trigger 「**" + super.getSpecificAbility(4985).getName() + "**」 on target after using this ability once", 1);
                break;
			case 9112:
				ret.getAilmentById(1601).setCastId(463);
				break;
			case 9113:
				ret.fixAddAuraEffect(1681);
				ret.fixMissingAuraAilment(1681, 688, null, Ailment.Target.Party);
		}
		return ret;
	}
}