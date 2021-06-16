package Unit10;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Tidus")
public class Tidus extends Unit{
	public Tidus() {
		super("Tidus", "haha", "hahaha", "crybaby", "star of the zanarkand abes");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{4618};
				break;
			case HP:
				ids = new int[]{5131};
				break;
			case S1:
				ids = new int[]{4616, 4617};
				break;
			case S2:
				ids = new int[]{4620, 4660};
				break;
			case EX:
				ids = new int[]{5130};
				break;
			case AA:
				ids = new int[]{5198};
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
			case 4617:
			case 4660:
				ret.addStaticHit("Enabled when ?**" + this.getSpecificAilment(1007).getName() + "**」 is active", 0);
				break;
		}
		return ret;
	}
}