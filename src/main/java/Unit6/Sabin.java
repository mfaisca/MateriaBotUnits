package Unit6;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Sabin")
public class Sabin extends Unit{
	public Sabin() {
		super("Sabin", "ink", "inkwelder", "mash");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{5983};
				break;
			case HP:
				ids = new int[]{5996};
				break;
			case S1:
				ids = new int[]{5981, 5982};
				break;
			case S2:
				ids = new int[]{5986, 5987};
				break;
			case EX:
				ids = new int[]{5992, 5993};
				break;
			case AA:
				ids = new int[]{5967};
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
			case 5987: //S2+
				ret.fixDelayHitData(6614);
			case 5982: //S1+
			case 5993: //EX+
				ret.addStaticHit("Enabled when 「**" + super.getSpecificAilment(1188).getName() + "**」 has 2+ stacks", 0);
				break;
		}
		return ret;
	}
}