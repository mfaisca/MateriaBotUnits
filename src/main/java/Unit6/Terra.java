package Unit6;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Terra")
public class Terra extends Unit{
	public Terra() {
		super("Terra", "tina");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{6766};
				break;
			case HP:
				ids = new int[]{6767};
				break;
			case S1:
				ids = new int[]{4416, 4444};
				break;
			case S2:
				ids = new int[]{4423, 4446};
				break;
			case EX:
				ids = new int[]{6575, 6769};
				break;
			case AA:
				ids = new int[]{4359};
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
	
	Boolean s1 = null, s2 = null;
	
	@Override
	public Ability getSpecificAbility(int id){
		Ability ret = super.getSpecificAbility(id);
		if(ret.getAilmentById(982) != null)
			ret.getAilmentById(982).setName("Magic");
		switch(ret.getId()) {
			case 4416:
				ret.fixMergeAbility(4417); break;
			case 4444:
				ret.addStaticHit("Enabled when 「**" + this.getSpecificAilment(982).getName() + "**」 has 5+ stacks", 0);
				ret.fixMergeAbility(4418); break;
			case 4423:
				ret.fixRemoveDispels();
				ret.fixMergeAbility(4424); break;
			case 4446:
				ret.fixRemoveDispels();
				ret.addStaticHit("Enabled when 「**" + this.getSpecificAilment(982).getName() + "**」 has 5+ stacks", 0);
				ret.fixMergeAbility(4425); break;
			case 6769:
				ret.fixRemoveDispels();
				ret.addStaticHit("Enabled after using 「**" + this.getSpecificAbility(6575).getName() + "**」 for 1 use");
				break;
		}
		return ret;
	}
	
	@Override
	public Ailment getSpecificAilment(int id){
		Ailment ret = super.getSpecificAilment(id);
		switch(id) {
			case 982:
				ret.setName("Magic");
		}
		return ret;
	}
}