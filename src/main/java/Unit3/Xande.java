package Unit3;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Xande")
public class Xande extends Unit{
	public Xande() {
		super("Xande");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{10622};
				break;
			case HP:
				ids = new int[]{10624};
				break;
			case S1:
				ids = new int[]{10816};
				break;
			case S2:
				ids = new int[]{10251, 10255};
				break;
			case EX:
				ids = new int[]{10259};
				break;
			case AA:
				ids = new int[]{10265};
				break;
			case LD:
				ids = new int[]{10261};
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
			case 10816: //S1
				ret.getAilmentById(1923).setMaxStacks(5);
				ret.fixMissingAuraAilment(1923, 933, null, Ailment.Target.Party);
				ret.addStaticAilmentEffect(1923, "+30% HP Damage to non-targets");
				ret.addStaticAilmentEffect(1923, "Each effect is unlocked depending on stacks", 0);
				break;
			case 10255: //S2+
				ret.addStaticHit("Enabled when 「**" + getSpecificAilment(1923).getName() + "**」 has 3+ stacks", 0);
				break;
			case 10259: //EX
				ret.getAilmentById(1925).setRank(-1);
				break;
			case 10261: //LD
				ret.getAilmentById(1923).setMaxStacks(5);
				ret.fixMissingAuraAilment(1923, 933, null, Ailment.Target.Party);
				ret.addStaticAilmentEffect(1923, "+30% HP Damage to non-targets");
				ret.addStaticAilmentEffect(1923, "Each effect is unlocked depending on stacks", 0);
				break;
		}
		return ret;
	}
}