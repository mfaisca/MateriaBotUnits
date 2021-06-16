package Unit4;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Kain")
public class Kain extends Unit{
	public Kain() {
		super("Kain");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{9454};
				break;
			case HP:
				ids = new int[]{9461};
				break;
			case S1:
				ids = new int[]{9453};
				break;
			case S2:
				ids = new int[]{9456};
				break;
			case EX:
				ids = new int[]{9460};
				break;
			case AA:
				ids = new int[]{7056};
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
			case 9460:
				ret.fixDelayHitData(6614);
				break;
		}
		return ret;
	}
}