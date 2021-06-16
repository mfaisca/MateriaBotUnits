package Unit6;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Cyan")
public class Cyan extends Unit{
	public Cyan() {
		super("Cyan");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{6912};
				break;
			case HP:
				ids = new int[]{6913};
				break;
			case S1:
				ids = new int[]{8178, 8180};
				break;
			case S2:
				ids = new int[]{8182, 8184};
				break;
			case EX:
				ids = new int[]{8187, 8391};
				break;
			case AA:
				ids = new int[]{7576};
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
			case 8178: //S1
			case 8182: //S2
				ret.addStaticHit("Free ability use", 0);
				break;
			case 8180: //S1+
			case 8184: //S2+
				ret.addStaticHit("Enabled when ?**" + super.getSpecificAilment(192).getName() + "**」 is active", 0);
				break;
			case 8391: //EX+
				ret.addStaticHit("Enabled when HP >= 80% Max HP", 0);
				break;
		}
		return ret;
	}
}