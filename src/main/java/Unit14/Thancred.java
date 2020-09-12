package Unit14;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Thancred")
public class Thancred extends Unit{
	public Thancred() {
		super("Thancred", "than", "thanc", "thonk", "thonkred");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{10007, 9880};
				break;
			case HP:
				ids = new int[]{9881};
				break;
			case S1:
				ids = new int[]{9869};
				break;
			case S2:
				ids = new int[]{9871};
				break;
			case EX:
				ids = new int[]{9878, 9879};
				break;
			case AA:
				ids = new int[]{5759};
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
		Ailment ailN = this.getSpecificAilment(-1);
		if(ailN != null) {
			ailN.setId(749);
			ailN.setName("Ninki");
			this.getAilments().remove(-1);
			this.getAilments().put(749, ailN);
		}
		ailN = this.getSpecificAilment(749);
		switch(ret.getId()) {
			case 9880: //BRV++ Ninki
			case 9879: //EX+
				ret.addStaticHit("Enabled when 「**" + super.getSpecificAilment(749).getName() + "**」 has 3 stacks", 0);
		}
		return ret;
	}
}