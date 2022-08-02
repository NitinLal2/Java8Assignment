package streamAPI.casestudy;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CandidateStreamingOperations {


		public static void main(String[] args) {
			
			List<Candidate> candidateList=new InterviewRepository().getCandidateList();
			
			//list candidate names from Pune city
			List<Candidate> listByCity=
					candidateList.stream().filter(t ->t.getCity()=="Pune").collect(Collectors.toList());
			System.out.println("List of Pune Candidates:"+listByCity);

			//list city and count of candidates per city
			List<String> cityList=candidateList.stream().map(Candidate::getCity).distinct().collect(Collectors.toList());
			cityList.forEach(city->{
				long count=candidateList.stream().filter(candidate ->city.equals(candidate.getCity())).count();
				System.out.println("Candidate count per city - "+ city +" : "+count);
			});
			
			

			//list technical expertise and count of candidates
			List<String> technicalExpertise=candidateList.stream().map(Candidate::getTechnicalExpertise).distinct().collect(Collectors.toList());
			technicalExpertise.forEach(expertise -> {
				long count=candidateList.stream().filter(candidate -> expertise.equals(candidate.getTechnicalExpertise())).count();
				System.out.println("Candidate count by Technical Expertise - "+expertise + " : "+count);
			});
			
			
			//list fresher candidates
			List<Candidate> fresherList=candidateList.stream().filter(candidate -> candidate.getYearsOfExperience()==0).collect(Collectors.toList());
			System.out.println("Fresher Candidate list : "+ fresherList);
			
			//listing candidates with highest experience
			Optional<Integer> highExp=candidateList.stream().sorted(Comparator.comparingInt(Candidate::getYearsOfExperience).reversed())
					.map(Candidate::getYearsOfExperience).findFirst();
			
			List<Candidate> candidatesHighestExp=candidateList.stream().filter(candidate -> candidate.getYearsOfExperience()==highExp.get()).collect(Collectors.toList());
					
			System.out.println("Sorted List of Candidates by Experience : "+candidatesHighestExp);

			//sort the candidates by city name
			candidateList.sort(Comparator.comparing(Candidate::getCity));
			printLine();
			System.out.println("Sorted List of Candidates by City Name");
		}

		private static void printLine() {
			// TODO Auto-generated method stub
			System.out.println("======================================================");
		}
	}
