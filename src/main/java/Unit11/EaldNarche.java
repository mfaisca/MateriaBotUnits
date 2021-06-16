package Unit11;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.EaldNarche")
public class EaldNarche extends Unit{
	public EaldNarche() {
		super("Eald'Narche", "eald'narshe", "ealdnarche", "ealdnarshe", "eald", 
				"elnacho", "el'nacho", "el nacho", "nacho", 
				"el donash", "eldonash", "el'donash");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{8575};
				break;
			case HP:
				ids = new int[]{8576};
				break;
			case S1:
				ids = new int[]{8678, 8020};
				break;
			case S2:
				ids = new int[]{8027, 8028};
				break;
			case EX:
				ids = new int[]{8573, 8574};
				break;
			case AA:
				ids = new int[]{8674};
				break;
			case LD:
				ids = new int[]{11620, 11621};
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
			case 8678: //S1
				ret.addStaticHit("Free ability use", 0);
				ret.removeHitDataById(9232); //Change Form?
				ret.removeAilmentById(1553); //Second Form Invisible Buff
				ret.removeHitDataById(8641); //S1 Charge - Useless
			case 8027: //S2
			case 8573: //EX
				Ailment ail = this.getSpecificAilment(1504);
				ail.setName("Exoplates");
				ret.addStaticHit("Enabled when ?**" + ail.getName() + "**」 is active", 0);
				break;
			case 8574: //EX+
				ret.removeAilmentById(1626);
				ret.removeHitDataById(9251);
				ret.addStaticHit("Raises HP Damage dealt by 20%");
				break;
		}
		return ret;
	}
}