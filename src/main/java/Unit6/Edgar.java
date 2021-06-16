package Unit6;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Edgar")
public class Edgar extends Unit{
	public Edgar() {
		super("Edgar");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{7090};
				break;
			case HP:
				ids = new int[]{7091};
				break;
			case S1:
				ids = new int[]{6817, 6818};
				break;
			case S2:
				ids = new int[]{6821, 6822};
				break;
			case EX:
				ids = new int[]{6825};
				break;
			case AA:
				ids = new int[]{6814};
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
			case 6825:
				ret.addStaticHit("BRV Hits ignore target's defense if afflicted with ?**" + super.getSpecificAilment(1342).getName() + "**」", 0);
				break;
		}
		return ret;
	}
}