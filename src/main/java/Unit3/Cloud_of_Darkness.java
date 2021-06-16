package Unit3;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Cloud_of_Darkness")
public class Cloud_of_Darkness extends Unit{
	public Cloud_of_Darkness() {
		super("Cloud of Darkness", "cod", "call of duty", "cloud of duty", "call of darkness");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{8471};
				break;
			case HP:
				ids = new int[]{8474};
				break;
			case S1:
				ids = new int[]{7650, 7651};
				break;
			case S2:
				ids = new int[]{7658, 7659};
				break;
			case EX:
				ids = new int[]{8468};
				break;
			case AA:
				ids = new int[]{7664};
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
		Ailment darkness = this.getSpecificAilment(1465);
		darkness.setName("Darkness");
		darkness.setRank(-1);
		if(ret.getDetails().getAilments().stream().noneMatch(a -> a.getName().equals(darkness.getName())))
			ret.getDetails().getAilments().add(darkness);
		switch(ret.getId()) {
			case 8471: //BRV
			case 7650: //S1
			case 8474: //HP
				break;
			case 7658: //S2
				ret.fixDelayHitData(6614);
				break;
			case 7659: //S2+
				ret.addStaticHit("Enabled when ?**" + darkness.getName() + "**」 has 5 stacks", 0);
				ret.addStaticHit("Free ability use", 0);
				ret.fixDelayHitData(5841);
				break;
			case 8468: //EX
				ret.fixDelayHitData(5126);
				break;
			case 7651: //Counter
				ret.getDetails().setMovementCost(30);
		}
		return ret;
	}
}