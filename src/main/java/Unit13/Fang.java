package Unit13;
import com.materiabot.GameElements.*;
import java.util.stream.Stream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Fang")
public class Fang extends Unit{
	public Fang() { super("Fang"); }
	
	@Override
	public void loadFixGL() {
		this.getSpecificAbility(15958).getAilments().add(this.getSpecificAilment(3263));
		this.getSpecificAbility(15958).getAilments().add(this.getSpecificAilment(3264));
		this.getSpecificAbility(15949).getAilments().remove(5);
		this.getSpecificAbility(15951).getAilments().remove(3);
		
		this.getSpecificAilment(3264).setName(new Text("Powerchain"));
		Stream.concat(this.getAilments().values().stream(), 
					  this.getAbilities().values().stream().flatMap(a -> a.getAilments().stream()))
			.filter(a -> a.getId() == 644 || a.getId() == 647)
			.forEach(a -> {
				if(a.getId() == 647)
					a.getArgs()[1] = a.getArgs()[0];
				else if(a.getId() == 644)
					for(int i = 0; i < a.getEffects().length; i++)
						if(a.getEffects()[i] == 22)
							a.getEffects()[i] = -1;
			});
	}
}