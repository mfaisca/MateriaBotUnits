package UnitT0;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Machina")
public class Machina extends Unit{
	public Machina() {
		super("Machina", "mach", "machy");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{5523};
				break;
			case HP:
				ids = new int[]{5521, 5522};
				break;
			case S1:
				ids = new int[]{5516};
				break;
			case S2:
				ids = new int[]{5527};
				break;
			case EX:
				ids = new int[]{5260, 5261};
				break;
			case AA:
				ids = new int[]{5531};
				break;
			case LD:
				ids = new int[]{10990, 9153};
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
			case 5522:
				ret.fixRemoveDispels();
				ret.addStaticHit("Enabled after breaking with BRV/HP/Awakening", 0);
				break;
			case 5260:
				ret.getAilmentById(1145).getEffects().get(4).val_type = Ailment.ValType.V3.getId();
				break;
			case 5261:
				ret.setName("Counter");
				break;
			case 10990:
				ret.addStaticAilmentEffect(1687, "Triggers 「**" + this.getSpecificAbility(9153).getName() + "**」 when attacking a target with 「**" + this.getSpecificAilment(1687).getName() + "**」");
				break;
			case 9153:
				ret.getDetails().setMovementCost(30);
				break;
		}
		return ret;
	}
}